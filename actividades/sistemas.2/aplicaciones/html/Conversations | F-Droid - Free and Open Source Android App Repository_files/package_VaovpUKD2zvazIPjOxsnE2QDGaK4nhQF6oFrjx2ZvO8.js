//@license magnet:?xt=urn:btih:0b31508aeb0634b347b8270c7bee4d411b5d4109&dn=agpl-3.0.txt AGPL-v3
// The code for the screenshots slider should be executed after screenshot images are loaded. so don't use this -> document.addEventListener('DOMContentLoaded',
window.addEventListener('load', function () {
    var slider = document.getElementById('screenshots');
    if (slider == null) {
        return;
    }

    // This class is used to display the entire slideshow differently if JS
    // is enabled. The absense of the 'slider' class will force all images
    // to be shown in a grid. The presence of it will show them inline and
    // hide those which don't fit.
    slider.className = slider.className + ' slider';

    var toShow = slider.querySelectorAll('.slider_nav');
    for (var i = 0; i < toShow.length; i ++) {
        toShow[i].style.display = 'block';
    }

    lory(slider, {rewind: true, enableMouseEvents: true});
});
// @license-end
