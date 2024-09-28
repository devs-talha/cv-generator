var newAddedEducationId = 0;

const addEducation = (cvId) => {
  newAddedEducationId -= 1;
  let element = `
              <div class="card mb-3" id="educationComponentId${newAddedEducationId}">
              <div class="card-body">
                <div class="container">
                  <div class="row">
                  <input type="hidden" name="educationId" value="-1">
                  <input type="hidden" name="educationCvId" value="${
                    cvId !== undefined ? cvId : -1
                  }">
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="educationName" required/>
                        <label for="educationName">Degree Name</label>
                      </div>
                    </div>
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="educationInstitute" required/>
                        <label for="educationInstitute">Institute Name</label>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="date" class="form-control" name="educationStartDate" required/>
                        <label for="educationStartDate">Start Date</label>
                      </div>
                    </div>
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="date" class="form-control" name="educationEndDate" />
                        <label for="educationEndDate">End Date</label>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="educationGrade" required/>
                        <label for="educationGrade">Grade</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <button type="button"
                class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                onclick="removeElement('educationComponentId${newAddedEducationId}')"
                >
                x
              </button>
            </div>
           `;

  document.getElementById("educations").appendChild(createElement(element));
};
