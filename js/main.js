$("#full").click(function(){
  checkBox("androidAnnotations", true);
  checkBox("restTemplate", true);
  checkBox("actionBarSherlock", true);
  checkBox("nineOldAndroids", true);
  checkBox("supportV4", true);
  checkRadio("tabNavigation", true);
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
});

$("#basic").click(function(){
  checkBox("androidAnnotations", true);
  checkBox("restTemplate", false);
  checkBox("actionBarSherlock", true);
  checkBox("nineOldAndroids", false);
  checkBox("supportV4", true);
  checkRadio("tabNavigation", true);
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
});

$("#rest").click(function(){
  checkBox("androidAnnotations", true);
  checkBox("restTemplate", true);
  checkBox("actionBarSherlock", true);
  checkBox("nineOldAndroids", false);
  checkBox("supportV4", true);
  checkRadio("tabNavigation", true);
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
});

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

function disableAndCheckSupportV4() {
  $checked1 = $("input:checkbox[name=nineOldAndroids]").prop("checked");
  $checked2 = $("input:checkbox[name=actionBarSherlock]").prop("checked");
  disableCheckbox("supportV4", $checked1 || $checked2);
  checkBox("supportV4", $checked1 || $checked2);
}

function disableCheckbox(name, disabled) {
  $target = $("input:checkbox[name="+ name +"]");
  $target.attr("disabled", disabled);
}

$("#kickstartr-form").submit(function() {

  var $name = $("input:text[name=name]").val();
  var $nameValidation = /^[a-zA-Z0-9]*$/;

  var $packageName = $("input:text[name=packageName]").val();
  var $packageValidation = /^([A-Za-z_]{1}[a-zA-Z0-9_]*(\.[a-zA-Z_]{1}[a-zA-Z0-9_]*)*)$/;

  if ($packageName != "" && !$packageValidation.test($packageName)) {
    alert('wrong package name');
    return false;
  }

  if ($name != "" && !$nameValidation.test($name)) {
    alert('wrong application name');
    return false;
  }

  return true;

})

