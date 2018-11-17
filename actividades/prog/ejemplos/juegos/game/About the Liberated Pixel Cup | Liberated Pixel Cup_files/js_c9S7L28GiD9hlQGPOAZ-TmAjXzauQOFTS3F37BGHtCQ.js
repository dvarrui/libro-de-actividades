/**
 * @file
 * SimpleAds JS Helper.
 */

(function ($) {
  Drupal.behaviors.simpleads = {
    attach: function(context) {
      var ad_type = $('#edit-field-ad-type select[id^=edit-field-ad-type]').val();
      var ad_text_format = $('#field-ad-text-add-more-wrapper select[id^=edit-field-ad-text]').val();
      _simpelads_switch_form(ad_type, ad_text_format);
      $('#edit-field-ad-type select[id^=edit-field-ad-type]').change(function(){
        ad_type = $(this).val();
        _simpelads_switch_form(ad_type, ad_text_format);
      });
      _simpelads_switch_form(ad_type, ad_text_format);
      $('#field-ad-text-add-more-wrapper select[id^=edit-field-ad-text]').change(function(){
        ad_text_format = $(this).val();
        _simpelads_switch_form(ad_type, ad_text_format);
      });
      var ad_block_limit = $('form#block-admin-configure #edit-ads-limit').val();
      var ad_block_rotation_type = $('form#block-admin-configure #edit-ads-rotation-type').val();
      _simpelads_switch_block_settings(ad_block_limit, false);
      $('form#block-admin-configure #edit-ads-limit').change(function(){
        _simpelads_switch_block_settings($(this).val(), false);
      });
      _simpelads_switch_block_settings(ad_block_rotation_type, 'delay');
      $('form#block-admin-configure #edit-ads-rotation-type').change(function(){
        _simpelads_switch_block_settings($(this).val(), 'delay');
      });
    }
  };
}(jQuery));

/**
 * Show/hide form elements.
 */
function _simpelads_switch_form(ad_type, p1) {
  (function ($) {
    el_image = $('form#simpleads-node-form #edit-field-ad-image');
    el_url = $('form#simpleads-node-form #edit-field-ad-url');
    el_url_target = $('form#simpleads-node-form #edit-field-ad-url-taget');
    el_flash = $('form#simpleads-node-form #edit-field-ad-flash');
    el_text = $('form#simpleads-node-form #edit-field-ad-text');
    if (ad_type == 'graphic') {
      el_image.show();
      el_url.show();
      el_url_target.show();
      el_flash.hide();
      el_text.hide();
    }
    else if (ad_type == 'text') {
      el_text.show();
      el_image.hide();
      el_url.hide();
      el_flash.hide();
      if (p1 == 'plain_text') {
        el_url.show();
        el_url_target.show();
      }
      else {
        el_url.hide();
        el_url_target.hide();
      }
    }
    else if (ad_type == 'flash') {
      el_url.show();
      el_url_target.show();
      el_flash.show();
      el_image.hide();
      el_text.hide();
    }
  }(jQuery));
}

/**
 * Show/hide block settings.
 */
function _simpelads_switch_block_settings(ad_setting_value, setting) {
  (function ($) {
    if (setting == false) {
      ad_rotation = $('form#block-admin-configure #ads_rotation_settings');
      if (ad_setting_value != 1)
        ad_rotation.show();
      else
        ad_rotation.hide();
    }
    else {
      if (setting == 'delay') {
        ad_rotation_delay = $('form#block-admin-configure #ads_rotation_settings .form-item-ads-rotation-delay');
        if (ad_setting_value == 0)
          ad_rotation_delay.hide();
        else
          ad_rotation_delay.show();
      }
    }
  }(jQuery));
}

/**
 * Ajax call for Ads.
 * elem - Ad container
 * tid  - term id
 * num - numer of ads to display
 * img_loader - image (ad load indicator), should be HTML tag <img src="loader.gif">
 */
function _simpelads_load(elem, tid, num, img_loader) {
  (function ($) {
    basepath = Drupal.settings.basePath;
    if (tid > 0 && num > 0) {
      if (img_loader != '')
        $(elem).html(img_loader);
      $.get(basepath + '?q=simpleads/load/' + tid + '/' + num, function (data) {
        $(elem).html(data);
      });
    }
  }(jQuery));
}

/**
 * jQuery Plugin.
 * SimpleAds rotator.
 */
(function ($) {
  $.simpleads_globals = {
    pos: []
  };
  $.simpleads_rotator = function(element, options) {
    this.options = {};
    element.data('simpleads_rotator', this);
    this.init = function (element, options) {
      this.options = $.extend({}, $.simpleads_rotator.defaultOptions, options);
      $.simpleads_globals.pos[options.delta] = 0;
      simpleads_start(element, this.options);
    };
    this.init(element, options);
  };

  $.fn.simpleads_rotator = function(options) {
    return this.each(function(){
      (new $.simpleads_rotator($(this), options));
    });
  }

  function run_rotation(element, options) {
    elem_id = element.attr('id');
    total_ads = $('#' + elem_id + " > div").size();
    if ($.simpleads_globals.pos[options.delta] == (total_ads - 1)) {
      $.simpleads_globals.pos[options.delta] = 0;
    }
    else {
      $.simpleads_globals.pos[options.delta]++;
    }

    $('#' + elem_id + " div").hide();
    var simpleads_elem = $('#' + elem_id + " > div:eq(" + $.simpleads_globals.pos[options.delta] + ")");
    
    if (options.rotation_type == 1) {
      simpleads_elem.show();
    }
    else if (options.rotation_type == 2) {
      simpleads_elem.fadeIn('fast');
    }
    else {
      simpleads_elem.show();
    }
    return false;
  }

  function simpleads_start(element, options) {
    run_rotation(element, options); 
    setInterval(function(){run_rotation(element, options);}, options.delay);
  }

  $.simpleads_rotator.defaultOptions = {
    rotation_type: 1,
    delay: 5,
    delta: 0
  };

}(jQuery));;
/**
 * @file
 * SimpleAds Campaigns JS helper.
 */

(function ($) {
  Drupal.behaviors.simpleads_campaigns = {
    attach: function(context) {
      var impressions = $('#edit-field-adcamp-impression input[id^=edit-field-adcamp-impression-]');
      _simpelads_campaigns_switch_form(impressions, 'impressions');
      $('#edit-field-adcamp-impression input[id^=edit-field-adcamp-impression-]').change(function(){
        _simpelads_campaigns_switch_form($(this), 'impressions');
      });
      var clicks = $('#edit-field-adcamp-click input[id^=edit-field-adcamp-click-]');
      _simpelads_campaigns_switch_form(clicks, 'clicks');
      $('#edit-field-adcamp-click input[id^=edit-field-adcamp-click-]').change(function(){
        _simpelads_campaigns_switch_form($(this), 'clicks');
      });
      var days = $('#edit-field-adcamp-day input[id^=edit-field-adcamp-day-]');
      _simpelads_campaigns_switch_form(days, 'days');
      $('#edit-field-adcamp-day input[id^=edit-field-adcamp-day-]').change(function(){
        _simpelads_campaigns_switch_form($(this), 'days');
      });

      var campaigns = $('#edit-campaign').val();
      if (campaigns == '_none') {
        $('#edit-field-ad-end-date').show();
      }
      else {
        $('#edit-field-ad-end-date').hide();
      }

      $('#edit-campaign').change(function(){
        if ($(this).val() == '_none') {
          $('#edit-field-ad-end-date').slideDown();
        }
        else {
          $('#edit-field-ad-end-date').slideUp();
        }
      });

    }
  };
}(jQuery));

/**
 * Show/hide form elements.
 */
function _simpelads_campaigns_switch_form(el, type) {
  (function ($) {
    el_impressions = $('form#simpleads-campaign-node-form #edit-field-adcamp-impressions');
    el_clicks = $('form#simpleads-campaign-node-form #edit-field-adcamp-clicks');
    el_days = $('form#simpleads-campaign-node-form #edit-field-adcamp-days');
    if (el.attr('checked') == true && type == 'impressions') {
      el_impressions.slideDown();
    }
    if (el.attr('checked') == false && type == 'impressions') {
      el_impressions.slideUp();
    }
    if (el.attr('checked') == true && type == 'clicks') {
      el_clicks.slideDown();
    }
    if (el.attr('checked') == false && type == 'clicks') {
      el_clicks.slideUp();
    }
    if (el.attr('checked') == true && type == 'days') {
      el_days.slideDown();
    }
    if (el.attr('checked') == false && type == 'days') {
      el_days.slideUp();
    }
  }(jQuery));
};
