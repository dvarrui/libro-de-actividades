// PMG Bootstrap framework
var pmgUtils = pmgUtils || {};
var pmgTools = pmgTools || {};
/* User controls namespace */
var pmgControls = pmgControls || {};

pmgUtils.BootUp = function () {
    //###Utility: BootUp
    //**Description:** This code boots up a page
    //
    //**Dependencies:** N/A
    currentPmgPage = $('.pmgCodePage');
}

pmgUtils.BootUp.prototype.run = function () {
    if (currentPmgPage) {
        try {
            var controlCount = 0;

            //grab all hidden div for control
            $('.pmg-controls').each(function (d, section) {
                //grab json string from div
                var jsonControl = JSON.parse($(section).val().replace(/'/g, "\""));
                //compile all controls

                var pmgFunctionVars = jsonControl[0].params.split('|');
                var pmgFunctionName = jsonControl[0].functionName;

                //reformat json string to add single quote
                var jsonFunctionVars = "";
                $(pmgFunctionVars).each(function (i, value) {
                    var val = value.split(':');
                    if (i < pmgFunctionVars.length - 1)
                        jsonFunctionVars += '"' + val[0] + '":"' + val[1] + '",';
                    else
                        jsonFunctionVars += '"' + val[0] + '":"' + val[1] + '"';
                });
                //run controls
                var pmgControl = window['pmgControls'][pmgFunctionName](JSON.parse("{" + jsonFunctionVars + "}"));
                controlCount++;
                return true;
            });
        } catch (err) {
            return false;
        }
    };
}

pmgUtils.BootUp.prototype.runSpecificInstance = function (inputElem) {
    try {
        var jsonControl = JSON.parse($(inputElem).val().replace(/'/g, "\""));
        var pmgFunctionVars = jsonControl[0].params.split('|');
        var pmgFunctionName = jsonControl[0].functionName;
        var jsonFunctionVars = "";

        $(pmgFunctionVars).each(function (i, value) {
            var val = value.split(':');
            if (i < pmgFunctionVars.length - 1)
                jsonFunctionVars += '"' + val[0] + '":"' + val[1] + '",';
            else
                jsonFunctionVars += '"' + val[0] + '":"' + val[1] + '"';
        });

        //run controls
        window['pmgControls'][pmgFunctionName](JSON.parse("{" + jsonFunctionVars + "}"));
    } catch (e) {
        console.log(err);
    }
};

$(document).ready(function () {
    var pmgOdcBoot = new pmgUtils.BootUp().run();

});