var newAddedContactInformationId = 0;

const addContactInformation = (cvId) => {
  newAddedContactInformationId -= 1;
  let element = `
              <div class="card mb-3" id="contactInformationComponentId${newAddedContactInformationId}">
              <div class="card-body">
                <div class="container">
                  <div class="row">
                  <input type="hidden" name="contactInformationId" value="-1">
                  <input type="hidden" name="contactInformationCvId" value="${cvId !== undefined ? cvId : -1}">
                    <div class="col-6">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="contactInformationName" required/>
                        <label for="contactInformationName">Name</label>
                      </div>
                    </div>
                    <div class="col-6">
                      <div class="form-floating">
                        <input type="text" class="form-control" name="contactInformationUrl" required/>
                        <label for="contactInformationUrl">URL</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <button type="button"
                class="opacity-0 hover-opacity-1 position-absolute top-0 start-100 translate-middle badge rounded-pill btn btn-danger"
                onclick="removeElement('contactInformationComponentId${newAddedContactInformationId}')"
                >
                x
              </button>
            </div>
           `;

  document.getElementById("contactInformations").appendChild(createElement(element));
}