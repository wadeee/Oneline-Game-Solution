package com.example.gamesolve.controller;

import com.example.gamesolve.bean.BreakWall;
import com.example.gamesolve.bean.Coordinate;
import com.example.gamesolve.bean.MapInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@RequestMapping
@Controller
public class GameSolutionController {

    @GetMapping
    public String indexGet(Model model) {

        List<List<BreakWall>> breakWallMap = newBreakWallMap();

        model.addAttribute("breakWallMap", breakWallMap);
        model.addAttribute("regionMap", new MapInformation());
        model.addAttribute("solutionMap", new MapInformation());

        return "index";
    }

    @PostMapping
    public String indexPost(@ModelAttribute("regionMap") MapInformation regionMap, Model model) {

        List<Coordinate> solution = gameSolution(regionMap);

        List<List<BreakWall>> breakWallMap = newBreakWallMap();
        for (int i = 1; i < solution.size(); i++) {
            Coordinate coordinateFirst = solution.get(i - 1);
            Coordinate coordinateSecond = solution.get(i);
            if (solution.get(i - 1).compareTo(solution.get(i)) > 0) {
                coordinateFirst = solution.get(i);
                coordinateSecond = solution.get(i - 1);
            }

            if (coordinateFirst.getX() == coordinateSecond.getX()) {
                breakWallMap.get(coordinateFirst.getX()).get(coordinateFirst.getY()).setRightBreak(true);
            } else {
                breakWallMap.get(coordinateFirst.getX()).get(coordinateFirst.getY()).setBottomBreak(true);
            }
        }

        model.addAttribute("breakWallMap", breakWallMap);
        model.addAttribute("solutionMap", regionMap);

        return "index";
    }

    private List<List<BreakWall>> newBreakWallMap() {

        List<List<BreakWall>> breakWallMap = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            List<BreakWall> breakWallRow = new ArrayList<>();
            for (int j = 0; j < 12; j++) {
                breakWallRow.add(new BreakWall());
            }
            breakWallMap.add(breakWallRow);
        }

        return breakWallMap;
    }

    private List<Coordinate> gameSolution(MapInformation mapInformation) {

        List<Integer> inDegree = cntInDegree(mapInformation);
        Coordinate start = findStartCoordinate(mapInformation);
        Stack<Coordinate> result = new Stack<>();

        gameSolutionSearch(mapInformation, start, inDegree, result);

        return result;
    }

    private boolean gameSolutionSearch(MapInformation mapInformation, Coordinate coordinate, List<Integer> inDegree, Stack<Coordinate> result) {

        List<List<Integer>> statusList = mapInformation.getStatus();
        if (inDegree.get(0) + inDegree.get(1) + inDegree.get(2) <= 0) return true;
        if (inDegree.get(0) + inDegree.get(1) > 2) return false;
        if (statusList.get(coordinate.getX()).get(coordinate.getY()) <= 0) return false;
        List<List<Integer>> inDegreeMap = mapInformation.getInDegree();

        int inDegreeBefore = inDegreeMap.get(coordinate.getX()).get(coordinate.getY());
        int statusBefore = statusList.get(coordinate.getX()).get(coordinate.getY());
        statusList.get(coordinate.getX()).set(coordinate.getY(), 0);
        inDegree.set(inDegreeBefore, inDegree.get(inDegreeBefore) - 1);
        removeBlock(mapInformation, coordinate, inDegree);
        result.push(coordinate);

        for (Coordinate side : Coordinate.fourSides) {
            int tempX = coordinate.getX() + side.getX();
            int tempY = coordinate.getY() + side.getY();
            if (gameSolutionSearch(mapInformation, new Coordinate(tempX, tempY), inDegree, result)) {
                statusList.get(coordinate.getX()).set(coordinate.getY(), statusBefore);
                return true;
            }
        }

        statusList.get(coordinate.getX()).set(coordinate.getY(), statusBefore);
        inDegree.set(inDegreeBefore, inDegree.get(inDegreeBefore) + 1);
        reuseBlock(mapInformation, coordinate, inDegree);
        result.pop();

        return false;
    }

    private void removeBlock(MapInformation mapInformation, Coordinate coordinate, List<Integer> inDegree) {

        List<List<Integer>> statusList = mapInformation.getStatus();
        List<List<Integer>> inDegreeMap = mapInformation.getInDegree();

        Arrays.stream(Coordinate.fourSides).forEach(side -> {
            int tempX = coordinate.getX() + side.getX();
            int tempY = coordinate.getY() + side.getY();
            if (statusList.get(tempX).get(tempY) <= 0) return;
            int blockInDegree = inDegreeMap.get(tempX).get(tempY);
            inDegreeMap.get(tempX).set(tempY, blockInDegree - 1);
            inDegree.set(blockInDegree, inDegree.get(blockInDegree) - 1);
            inDegree.set(blockInDegree - 1, inDegree.get(blockInDegree - 1) + 1);
        });
    }

    private void reuseBlock(MapInformation mapInformation, Coordinate coordinate, List<Integer> inDegree) {

        List<List<Integer>> statusList = mapInformation.getStatus();
        List<List<Integer>> inDegreeMap = mapInformation.getInDegree();

        Arrays.stream(Coordinate.fourSides).forEach(side -> {
            int tempX = coordinate.getX() + side.getX();
            int tempY = coordinate.getY() + side.getY();
            if (statusList.get(tempX).get(tempY) <= 0) return;
            int blockInDegree = inDegreeMap.get(tempX).get(tempY);
            inDegreeMap.get(tempX).set(tempY, blockInDegree + 1);
            inDegree.set(blockInDegree, inDegree.get(blockInDegree) - 1);
            inDegree.set(blockInDegree + 1, inDegree.get(blockInDegree + 1) + 1);
        });
    }

    private Coordinate findStartCoordinate(MapInformation mapInformation) {

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (mapInformation.getStatus().get(i).get(j).equals(2)) {
                    return new Coordinate(i, j);
                }
            }
        }

        return new Coordinate(-1, -1);
    }

    private List<Integer> cntInDegree(MapInformation mapInformation) {

        List<Integer> result = Arrays.asList(new Integer[]{0, 0, 0, 0, 0});
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (mapInformation.getStatus().get(i).get(j) > 0) {
                    int inDegreeTemp = fourSidesInDegree(mapInformation, new Coordinate(i, j));
                    result.set(inDegreeTemp, result.get(inDegreeTemp) + 1);
                    mapInformation.getInDegree().get(i).set(j, inDegreeTemp);
                }
            }
        }

        return result;
    }

    private int fourSidesInDegree(final MapInformation mapInformation, Coordinate coordinate) {
        int result = 0;
        for (Coordinate side : Coordinate.fourSides) {
            result += (mapInformation.getStatus().get(coordinate.getX() + side.getX()).get(coordinate.getY() + side.getY()) > 0) ? 1 : 0;
        }
        return result;
    }

}
