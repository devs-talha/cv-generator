const createElement = (str) => {
  let template = document.createElement("template");
  template.innerHTML = str.trim();
  return template.content.firstChild;
};

const removeElement = (id) => {
  document.getElementById(id).remove();
};

const allElementsUnique = (elementName) => {
  let valuesArray = [...document.getElementsByName(elementName)].map((ele) =>
    ele.value.trim().toLowerCase()
  );
  let valuesSet = new Set(valuesArray);
  return valuesArray.length === valuesSet.size;
};
