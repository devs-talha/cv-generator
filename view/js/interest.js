var newAddedInterestId = 0;

const addInterest = (cvId) => {
  newAddedInterestId -= 1;
  let element = `<div class="col-4 col-md-2 mb-3" id="interestComponentId${newAddedInterestId}">
              <input type="hidden" name="interestId" value="-1" />
              <input type="hidden" name="interestCvId" value="${
                cvId !== undefined ? cvId : -1
              }" />
                <div class="form-floating">
                  <input type="text" class="form-control position-relative" name="interestName" required/>
                  <button type="button"
                    class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                    onclick="removeElement('interestComponentId${newAddedInterestId}')"
                    >
                    x
                  </button>
                  <label for="interestName">Name</label>
                </div>
              </div>`;

  document.getElementById("interests").appendChild(createElement(element));
};

const checkInterestsUniqueness = () => {
  if (!allElementsUnique("interestName")) {
    document.getElementById("interestsHelper").innerHTML =
      "Interests must be unique";
      document.getElementById("interestsHelper").focus();
    return false;
  } else {
    document.getElementById("interestsHelper").innerHTML = "";
    return true;
  }
};
