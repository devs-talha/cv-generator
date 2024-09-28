const handleChangePassword = () => {
  if (
    document.getElementsByName("newPassword")[0].value.trim().toLowerCase() !==
    document.getElementsByName("confirmPassword")[0].value.trim().toLowerCase()
  ) {
    alert("New password and confirm password do not match");
    return false;
  }
  return true;
};
