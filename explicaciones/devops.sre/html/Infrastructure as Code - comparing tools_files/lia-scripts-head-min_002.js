;(function(){LITHIUM.AngularSupport=function(){var app;var injector;var options={coreModule:'li.community',coreModuleDeps:[],noConflict:true,bootstrapElementSelector:'.lia-page .min-width .lia-content',bootstrapApp:true,debugEnabled:false,useCsp:true,useNg2:false}
var getAbsoluteUrl=(function(){var a;return function(url){if(!a){a=document.createElement('a');}
a.href=url;return a.href;};})();LITHIUM.Angular={};function init(){var options=getOptions();var bootModules=[];var bootstrapElement=document.querySelector(options.bootstrapElementSelector);bootModules.push(options.coreModule);if(options.customerModules&&options.customerModules.length>0){bootModules.concat(options.customerModules);}
if(options.useCsp){bootstrapElement.setAttribute('ng-csp','no-unsafe-eval');bootstrapElement.setAttribute('li-common-non-bindable','');}
app=LITHIUM.angular.module(options.coreModule,options.coreModuleDeps);app.config(['$locationProvider','$provide','$injector','$logProvider','$compileProvider','$qProvider','$anchorScrollProvider',function($locationProvider,$provide,$injector,$logProvider,$compileProvider,$qProvider,$anchorScrollProvider){$anchorScrollProvider.disableAutoScrolling();var baseElm=document.createElement('base');baseElm.setAttribute('href',getAbsoluteUrl(location));document.getElementsByTagName('head')[0].appendChild(baseElm);if(window.history&&window.history.pushState){$locationProvider.html5Mode({enabled:true,requireBase:true,rewriteLinks:false}).hashPrefix('!');}
$logProvider.debugEnabled(options.debugEnabled);$compileProvider.debugInfoEnabled(options.debugEnabled);if($injector.has('$uibModal')){$provide.decorator('$uibModal',['$delegate',function($delegate){var open=$delegate.open;$delegate.open=function(opts){opts.backdropClass=(opts.backdropClass?opts.backdropClass+' ':'')+'lia-modal-backdrop';opts.windowClass=(opts.windowClass?opts.windowClass+' ':'')+'lia-modal-window';return open(opts);}
return $delegate;}]);}
if($injector.has('uibDropdownConfig')){var uibDropdownConfig=$injector.get('uibDropdownConfig');uibDropdownConfig.openClass='lia-dropdown-open';}
if($injector.has('uibButtonConfig')){var uibButtonConfig=$injector.get('uibButtonConfig');uibButtonConfig.activeClass='lia-link-active';}
$qProvider.errorOnUnhandledRejections(false);}]);if(options.bootstrapApp){if(options.useNg2){injector=LITHIUM.Angular.upgradeAdapter.bootstrap(bootstrapElement,bootModules);}else{injector=LITHIUM.angular.bootstrap(bootstrapElement,bootModules);}}else{LITHIUM.Loader.onLoad(function(){injector=LITHIUM.angular.element(bootstrapElement).injector();});}
LITHIUM.Angular.app=app;if(options.noConflict){}}
function isAngularEnabled(){return app!==undefined;}
function compile(content){if(content===undefined){content=document.querySelector(options.bootstrapElementSelector);}
var compiled;if(content===undefined||content===''){return content;}
injector.invoke(['$rootScope','$compile',function($rootScope,$compile){var $elm;try{$elm=LITHIUM.angular.element(content);}catch(e){$elm=LITHIUM.angular.element('<li:safe-wrapper>'+content+'</li:safe-wrapper>');}
$elm.attr('li-common-non-bindable','');compiled=$compile($elm)($rootScope);$rootScope.$digest();}]);return compiled;}
function updateLocationUrl(url,replaceState){injector.invoke(['$location','$rootScope','$browser',function($location,$rootScope,$browser){url=(url==='')?'?':url;$location.url(url,replaceState);$rootScope.$apply();}]);}
function setOptions(newOptions){return extend(options,newOptions)}
function getOptions(){return options;}
function initGlobal(angular){LITHIUM.angular=angular;if(getOptions().useNg2){LITHIUM.Angular.upgradeAdapter=new ng.upgrade.UpgradeAdapter();}}
function extend(target,source){target=target||{};for(var prop in source){if(Object.prototype.toString.call(source[prop])==='[object object]'){target[prop]=extend(target[prop],source[prop]);}else{target[prop]=source[prop];}}
return target;}
return{preventGlobals:LITHIUM.Globals.preventGlobals,restoreGlobals:LITHIUM.Globals.restoreGlobals,init:init,compile:compile,isAngularEnabled:isAngularEnabled,updateLocationUrl:updateLocationUrl,setOptions:setOptions,getOptions:getOptions,initGlobal:initGlobal}}();})();
