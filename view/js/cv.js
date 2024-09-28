const handleDelete = () => {
  if (Number.parseInt(document.getElementsByName("id")[0].value) === -1) {
    document.getElementById("backButton").click();
    return false;
  }
  return confirm("Confirm delete the CV?");
};

const handleSave = () => {
  let valid = true;
  if (!checkInterestsUniqueness()) valid = false;
  if (!checkLanguagesUniqueness()) valid = false;
  if (!checkSkillsUniqueness()) valid = false;

  return valid;
};

const handlePreview = () => {
  if (Number.parseInt(document.getElementsByName("id")[0].value) === -1) {
    alert("CV isn't saved yet. Consider saving it before generating preview.");
    return false;
  }
  return true;
};
