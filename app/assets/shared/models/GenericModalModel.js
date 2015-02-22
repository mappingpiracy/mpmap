mpmap.service('GenericModalModel', function() {

	var genericModal = {
		show: false,
		title: null,
		value: null,
		open: openModal(title, value),
		close: closeModal()
	};

	function openModal(title, value) {
		console.log(this.genericModal);
	}

	function closeModal() {
		console.log(this.genericModal);
	}

	return function() {
		return genericModal;
	};
});