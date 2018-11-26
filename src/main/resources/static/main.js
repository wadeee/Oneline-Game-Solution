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

    $("#format-select").change(function () {
        var length = parseInt($(this).val());
        for (var i = 1; i <= length; i++) {
            for (var j = 1; j <= length; j++) {
                $("#status[" + i + "][" + j + "]").val(1);
                refreshBlock($("#status[" + i + "][" + j + "]"));
            }
        }
    })
})

clickTimeout = {
    _timeout: null,
    set: function (fn) {
        var that = this;
        that.clear();
        that._timeout = window.setTimeout(fn, 300);
    },
    clear: function () {
        var that = this;
        if (that._timeout) {
            window.clearTimeout(that._timeout);
        }
    }
};

function refreshBlock($mapBlock) {
    $mapBlock.removeClass("map-block-0");
    $mapBlock.removeClass("map-block-1");
    $mapBlock.removeClass("map-block-2");
    $mapBlock.addClass("map-block-" + $mapBlock.find("input").val());
}
