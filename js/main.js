$("#full").click(function(){
  fullPreset();
});

function fullPreset() {
  checkBox("androidAnnotations", true);
  checkBox("restTemplate", true);
  checkBox("actionBarSherlock", true);
  checkBox("nineOldAndroids", true);
  checkBox("supportV4", true);
  checkBox("acra", true);
  checkBox("viewPager", true);
  checkRadio("tabNavigation", true);
  $("#tab").show();
  $("#list").hide();
  $("#none").hide();
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
}

$("#basic").click(function(){
  basicPreset();
});

function basicPreset() { 
  checkBox("androidAnnotations", true);
  checkBox("restTemplate", false);
  checkBox("actionBarSherlock", true);
  checkBox("nineOldAndroids", false);
  checkBox("supportV4", true);
  checkBox("viewPager", false);
  checkBox("acra", false);
  checkRadio("tabNavigation", true);
  $("#tab").show();
  $("#list").hide();
  $("#none").hide();
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
};

$("#rest").click(function(){
  restPreset();
});

function restPreset() {
  checkBox("androidAnnotations", true);
  checkBox("restTemplate", true);
  checkBox("actionBarSherlock", true);
  checkBox("nineOldAndroids", false);
  checkBox("supportV4", true);
  checkBox("viewPager", false);
  checkBox("acra", false);
  checkRadio("tabNavigation", true);
  $("#tab").show();
  $("#list").hide();
  $("#none").hide();
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
}

function checkBox(name, checked) {
  $("input:checkbox[name="+ name +"]").attr("checked", checked);
};

function checkRadio(value, checked) {
  $("input:radio[value="+ value +"]").attr("checked", checked);
};

$("input:checkbox[name=actionBarSherlock]").click(function(event) {
  var $target = $(event.target);
  var $checked = $target.prop("checked");
  $("input:radio[name=navigationType]").attr("disabled", !$checked);
  if (!$checked) {
    $("input:radio[value=none]").attr("checked", true);
  }
  disableAndCheckSupportV4();
});

$("input:checkbox[name=nineOldAndroids]").click(function(event) {
  disableAndCheckSupportV4();
});

$("input:checkbox[name=viewPager]").click(function(event) {
  disableAndCheckSupportV4();
});

function disableAndCheckSupportV4() {
  $checked1 = $("input:checkbox[name=nineOldAndroids]").prop("checked");
  $checked2 = $("input:checkbox[name=actionBarSherlock]").prop("checked");
  $checked3 = $("input:checkbox[name=viewPager]").prop("checked");
  disableCheckbox("supportV4", $checked1 || $checked2 || $checked3);
  checkBox("supportV4", $checked1 || $checked2 || $checked3);
}

function disableCheckbox(name, disabled) {
  $target = $("input:checkbox[name="+ name +"]");
  $target.attr("disabled", disabled);
}

$("#kickstartr-form").submit(function() {

  var $name = $("input:text[name=name]").val();
  var $nameValidation = /^[a-zA-Z0-9]*$/;

  var $activity = $("input:text[name=activity]").val();
  var $activityValidation = /^[a-zA-Z0-9]*$/;

  var $packageName = $("input:text[name=packageName]").val();
  var $packageValidation = /^([A-Za-z_]{1}[a-zA-Z0-9_]*(\.[a-zA-Z_]{1}[a-zA-Z0-9_]*)*)$/;

  if ($packageName != "" && !$packageValidation.test($packageName)) {
    $("span[class=help-inline]").show();
    return false;
  }

  if ($name != "" && !$nameValidation.test($name)) {
    $("span[class=help-inline]").show();
    return false;
  }

  if ($activity != "" && !$activityValidation.test($activity)) {
    $("span[class=help-inline]").show();
    return false;
  }

  $("span[class=help-inline]").hide();
  return true;

});

$("input:radio[value=none]").click(function() {
  $("#tab").hide();
  $("#list").hide();
  $("#none").show();
});

$("input:radio[value=listNavigation]").click(function() {
  $("#tab").hide();
  $("#list").show();
  $("#none").hide();
});

$("input:radio[value=tabNavigation]").click(function() {
  $("#tab").show();
  $("#list").hide();
  $("#none").hide();
});

$('.tips').tooltip();

// Select the basic Preset by default 
$("#basic").button("toggle");
basicPreset();

$("#tab").show();
$("#list").hide();

$("span[class=help-inline]").hide();
