var newAddedLanguageId = 0;
const addLanguage = (cvId) => {
  newAddedLanguageId -= 1;
  let element = `<div class="col-4 col-md-2 mb-3" id="languageComponentId${newAddedLanguageId}">
              <input type="hidden" name="languageId" value="-1" />
              <input type="hidden" name="languageCvId" value="${
                cvId !== undefined ? cvId : -1
              }" />
                <div class="form-floating">
                  <input type="text" class="form-control position-relative" name="languageName" required/>
                  <button type="button"
                    class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                    onclick="removeElement('languageComponentId${newAddedLanguageId}')"
                    >
                    x
                  </button>
                  <label for="languageName">Name</label>
                </div>
              </div>`;

  document.getElementById("languages").appendChild(createElement(element));
};

const checkLanguagesUniqueness = () => {
  if (!allElementsUnique("languageName")) {
    document.getElementById("languagesHelper").innerHTML =
      "Languages must be unique";
    document.getElementById("languagesHelper").focus();
    return false;
  } else {
    document.getElementById("languagesHelper").innerHTML = "";
    return true;
  }
};
