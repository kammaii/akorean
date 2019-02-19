// Web Storage (HTML 5)

/**
 * If the key exists in Web Storage, use value.
 * If the key doesn't exist yet, set it.
 **/
function getItem(key, defaultValue) {
  let value = localStorage.getItem(key);
  if(value != undefined) {
    console.log("Found: " + key);
    console.log(value);
  } else {
    value = defaultValue;
    localStorage.setItem(key, value);
    console.log("Created web storage key " + key + ", value: " + value);
  }
  return value;
}

function getBooleanItem(key, defaultValue) {
  let val = getItem(key, defaultValue);
  if(val === "false") {
    return false
  } else  {
    return val;
  }
}

function getTutorialToggle() {
  return getBooleanItem("tutorialToggle", true);
}

function setTutorialToggle(newValue) {
  localStorage.setItem("tutorialToggle", newValue);
  console.log("Set tutorialToggle to: " + newValue);
}

$( document ).ready(function() {

  // Set the toggle based on the value found in web storage

  $('#tutorialToggle').prop('checked', getTutorialToggle());

  $('#tutorialToggle').click(function(event) {
      let newValue = event.target.checked;
      setTutorialToggle(newValue);
  });

});
