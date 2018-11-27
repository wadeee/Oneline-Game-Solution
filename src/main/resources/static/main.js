$(function () {
    $("td").each(function () {
        refreshBlock($(this));
    });

    $(".map-block").click(function () {
        var $input = $(this).find("input");
        var val = parseInt($input.val());
        $input.val((1 + val) % 2);
        refreshBlock($(this));
    });

    $(".map-block").dblclick(function () {
        var $input = $(this).find("input");
        $input.val(2);
        refreshBlock($(this));
    })

    $("#format-select-height").change(blocksInit);

    $("#format-select-width").change(blocksInit);
})

blocksInit = function () {
    for (var i = 1; i <= 10; i++) {
        for (var j = 1; j <= 10; j++) {
            $("[name='status[" + i + "][" + j + "]']").val(0);
            console.log("[name='status[" + i + "][" + j + "]']");
            refreshBlock($("[name='status[" + i + "][" + j + "]']").parent());
        }
    }
    var height = parseInt($("#format-select-height").val());
    var width = parseInt($("#format-select-width").val());
    console.log(height);
    console.log(width);
    for (var i = 1; i <= height; i++) {
        for (var j = 1; j <= width; j++) {
            $("[name='status[" + i + "][" + j + "]']").val(1);
            console.log("[name='status[" + i + "][" + j + "]']");
            refreshBlock($("[name='status[" + i + "][" + j + "]']").parent());
        }
    }
}

refreshBlock = function ($mapBlock) {
    $mapBlock.removeClass("map-block-0");
    $mapBlock.removeClass("map-block-1");
    $mapBlock.removeClass("map-block-2");
    $mapBlock.addClass("map-block-" + $mapBlock.find("input").val());
}
