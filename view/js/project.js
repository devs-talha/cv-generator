var newAddedProjectId = 0;

const addProject = (cvId) => {
  newAddedProjectId -= 1;
  let element = `<div class="card mb-3" id="projectComponentId${newAddedProjectId}">
                <div class="card-body">
                  <div class="container">
                    <div class="row">
                    <input type="hidden" name="projectId" value="-1" />
                    <input type="hidden" name="projectCvId" value="${cvId !== undefined ? cvId : -1}" />
                      <div class="col-12 mb-3">
                        <div class="form-floating">
                          <input type="text" class="form-control" name="projectName" required/>
                          <label for="projectName">Name</label>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-12">
                        <div class="form-floating">
                          <textarea class="form-control" name="projectDetails" required> </textarea>
                          <label for="projectDetails">Details</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <button type="button"
                  class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                  onclick="removeElement('projectComponentId${newAddedProjectId}')"
                  >
                  x
                </button>
              </div>`;

  document.getElementById("projects").appendChild(createElement(element));
}