var newAddedSkillId = 0;

const addSkill = (cvId) => {
  newAddedSkillId -= 1;
  let element = `<div class="col-4 col-md-2 mb-3" id="skillComponentId${newAddedSkillId}">
              <input type="hidden" name="skillId" value="-1" />
              <input type="hidden" name="skillCvId" value="${
                cvId !== undefined ? cvId : -1
              }" />
                <div class="form-floating">
                  <input type="text" class="form-control position-relative" name="skillName" required/>
                  <button type="button"
                    class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                    onclick="removeElement('skillComponentId${newAddedSkillId}')"
                    >
                    x
                  </button>
                  <label for="skillName">Name</label>
                </div>
              </div>`;

  document.getElementById("skills").appendChild(createElement(element));
};

const checkSkillsUniqueness = () => {
  if (!allElementsUnique("skillName")) {
    document.getElementById("skillsHelper").innerHTML = "Skills must be unique";
    document.getElementById("skillsHelper").focus();
    return false;
  } else {
    document.getElementById("skillsHelper").innerHTML = "";
    return true;
  }
};
