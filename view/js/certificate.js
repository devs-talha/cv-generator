var newAddedCertificateId = 0;

const addCertificate = (cvId) => {
  newAddedCertificateId -= 1;
  let element = `<div class="card mb-3" id="certificateComponentId${newAddedCertificateId}">
              <div class="card-body">
                <div class="container">
                  <div class="row">
                    <input type="hidden" name="certificateId" value="-1" />
                    <input type="hidden" name="certificateCvId" value="${cvId !== undefined ? cvId : -1}" />
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="certificateName" required/>
                        <label for="certificateName">Certificate Name</label>
                      </div>
                    </div>
                    <div class="col-md-6 mb-3">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="certificateInstitute" required/>
                        <label for="certificateInstitute">Institute Name</label>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-floating">
                        <input type="date" class="form-control" name="certificateCompletionDate" required/>
                        <label for="certificateCompletionDate">Completion Date</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <button type="button"
                class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                onclick="removeElement('certificateComponentId${newAddedCertificateId}')"
                >
                x
              </button>
            </div>`;

  document.getElementById("certificates").appendChild(createElement(element));
}
