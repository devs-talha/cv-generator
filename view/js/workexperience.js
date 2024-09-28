var newAddedWorkExperienceId = 0;

const addWorkExperience = (cvId) => {
  newAddedWorkExperienceId -= 1;
  let element = `<div class="card mb-3" id="workExperienceComponentId${newAddedWorkExperienceId}">
                <div class="card-body">
                  <div class="container">
                    <div class="row">
                      <input type="hidden" name="workExperienceId" value="-1" />
                      <input type="hidden" name="workExperienceCvId" value="${cvId !== undefined ? cvId : -1}" />
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="text" class="form-control" name="workExperienceRole" required/>
                          <label for="workExperienceRole">Role</label>
                        </div>
                      </div>
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="text" class="form-control" name="workExperienceInstitute" required/>
                          <label for="workExperienceInstitute">Institute Name</label>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="date" class="form-control" name="workExperienceStartDate" required/>
                          <label for="workExperienceStartDate">Start Date</label>
                        </div>
                      </div>
                      <div class="col-md-6 mb-3">
                        <div class="form-floating">
                          <input type="date" class="form-control" name="workExperienceEndDate" />
                          <label for="workExperienceEndDate">End Date</label>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-12">
                        <div class="form-floating">
                          <textarea class="form-control" name="workExperienceDetails" required> </textarea>
                          <label for="workExperienceDetails">Details</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <button type="button"
                  class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                  onclick="removeElement('workExperienceComponentId${newAddedWorkExperienceId}')"
                  >
                  x
                </button>
              </div>`;

  document.getElementById("workExperiences").appendChild(createElement(element));
}