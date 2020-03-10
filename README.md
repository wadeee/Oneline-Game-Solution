# 最强连一连解法

最强连一连（微信小游戏）的游戏解法辅（作）助（弊）工具

## 游戏介绍


+ 棋盘大小

  N×M（N,M < 10）
  
  参考数据： 
    
    - 5×5（一级第100关）
    - 6×7（十级第100关）

  游戏总共十六级，按照前面的这个趋势来看，棋盘不会超过9×9(由于关数太多了，不是很有空玩通关，所以就猜测了一下棋盘极限大小)


+ 棋盘元素

  + 起点格子
    
    游戏的起点

  + 普通格子
    
    普通格子用来和起点格子区分
    
    任意的普通格子都可以是终点
    
  + 禁用格子
  
    在N×N的棋盘上布满了起点格子和普通格子
    
    剩下的不可走的区域就是禁用格子
    
## Docker下运行

+ Docker已经安装好并启动

+ 进入项目目录，构建image并查看镜像是否生成

  ```bash
  docker build -t onelinegamesolution ./Dockerfile
  docker images
  ```

+ 生成并运行container映射3001端口到3001端口（3001端口可以任意更改）

  ```bash
  docker run -itd -p 8080:3001 --name=onelinegamesolutioncontainer onelinegamesolution
  ```

+ 打开[http://localhost:8080/]

    - 没有浏览器的情况下可以用curl打开查看是否正常运行
  
        ```shell script
        curl http://localhost:8080/
        ```

+ 使用完后删除image和container

    ```bash
    docker stop onelinegamesolutioncontainer && docker rm onelinegamesolutioncontainer && docker image rm onelinegamesolutioncontainer
    ```


## 算法思路

+ 深度优先搜索
  
  这是个路径探索类的游戏，最容易让人想起来的算法就是深搜和广搜。
  
  当然这个很明显要用深搜来解。
  
  时间复杂度：O(3<sup>NM</sup>)

  + 初步剪枝
  
    初步剪枝很容易，将明显错误的路线及时剪枝即可。
    
    路径搜索的过程中，遇到入度为0(禁用格子或者已被走过的情况)的格子，就可以将此步以后的搜索剪枝了。
    
  + 出入度统计剪枝
  
    每个还没被走的格子，它的出入度都会是{0, 1, 2, 3, 4}
    
    针对出入度统计我们可以实施进行维护，每走一步，我们都更新周围四个格子的出入度即可（回溯的时候记得恢复出入度）
    
    出入度为0和1的格子一定是终点，但终点只能有一个
    
    所以出入度为0和1的格子数，不能超过一个，超过一个即可剪枝。
    
  + ~~区域分割剪枝~~
  
    当棋盘剩余的区域被分为两个部分或者多个部分的时候，就可以将其剪枝。
    
    因为往任何一个区域走，都会导致另外一个区域永远无法走到。
    
    但我们发现一个问题，区域分割需要进行一次地图遍历，这个耗费过高，所以不使用这个剪枝方案。
