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
  checkBox("roboguice", false);
  checkRadio("tabNavigation", true);
  $("#tab").show();
  $("#list").hide();
  $("#none").hide();
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
  disableCheckbox("roboguice", true);
  disableCheckbox("androidAnnotations", false);
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
  checkBox("roboguice", false);
  checkBox("acra", false);
  checkRadio("tabNavigation", true);
  $("#tab").show();
  $("#list").hide();
  $("#none").hide();
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
  disableCheckbox("roboguice", true);
  disableCheckbox("androidAnnotations", false);
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
  checkBox("roboguice", false);
  checkBox("acra", false);
  checkRadio("tabNavigation", true);
  $("#tab").show();
  $("#list").hide();
  $("#none").hide();
  $("input:radio[name=navigationType]").attr("disabled", false);
  disableCheckbox("supportV4", true);
  disableCheckbox("roboguice", true);
  disableCheckbox("androidAnnotations", false);
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

$("input:checkbox[name=roboguice]").click(function(event) {
  $checked = $(event.target).prop("checked");
  disableCheckbox("androidAnnotations", $checked);
  checkBox("androidAnnotations", false);
});

$("input:checkbox[name=androidAnnotations]").click(function(event) {
  $checked = $(event.target).prop("checked");
  disableCheckbox("roboguice", $checked);
  checkBox("roboguice", false);
});

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

function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function getUrlVar(name) {
    var urlVar = getUrlVars()[name];
    if (urlVar != undefined) {
        return getUrlVars()[name].replace(/\/+$/, "");
    } else {
        return "";
    }

}

function getAccessTokenVar() {
    return getUrlVar('accessToken').slice(0, 40);
}

function getErrorMessage() {
    return decodeURIComponent(getUrlVar('error'));
}

function getSuccessMessage() {
    return decodeURIComponent(getUrlVar('success'));
}

function getRepositoryUrl() {
    return decodeURIComponent(getUrlVar('repositoryUrl'));
}

function setError(divClass) {
    var err = getErrorMessage();
    if (err != "") {
        $(divClass).append("<strong>ERROR! </strong>" + err);
        $(divClass).show();
    }
}

function setSuccess(divClass) {
    var success = getSuccessMessage();
    if (success != "") {
        var repositoryUrl = getRepositoryUrl();
        $(divClass).append("<strong>SUCCESS! </strong>" + success);
        if (repositoryUrl != "") {
            $(divClass).append('<a href="' + repositoryUrl + '">' + repositoryUrl + '</a>');
        }
        $(divClass).show();
    }

}

function setInformation(divClass) {
    var token = getAccessTokenVar();
    if (token != "") {
        var message = '<p>Access token retrieved from Github : <strong>' + token + '</strong>.</p><p class="text-center">You can now get your application uploaded there using the button at the end of the form.</p>';
        $(divClass).append(message);
        $(divClass).show();
        $('#githubRequest').hide();
        $('#download').text("Push on Github");
    }
}