let inputElement = document.getElementById("InputPhoto");
let labelInputElement = document.getElementById("LabelInputPhoto");

inputElement.addEventListener("change", handleFiles, false);

function handleFiles() {
	for (let i = 0, numFiles = this.files.length; i < numFiles; i++) {
		let file = this.files[i];
		labelInputElement.innerHTML = file.name;
		
		let img = document.createElement("img");
		img.classList.add("img-fluid");
		img.alt = "Aperçu de l'article'"
		img.file = file;
		document.getElementById("containerImg").appendChild(img);
		
		let reader = new FileReader();
		reader.onload = (function(aImg) { return function(e) { aImg.src = e.target.result; }; })(img);
		reader.readAsDataURL(file);
	}
}