const handleSignup = () => {
  if (
    document.getElementsByName("password")[0].value.trim().toLowerCase() !==
    document.getElementsByName("confirmPassword")[0].value.trim().toLowerCase()
  ) {
    alert("Password and confirm password do not match");
    return false;
  }
  return true;
};
