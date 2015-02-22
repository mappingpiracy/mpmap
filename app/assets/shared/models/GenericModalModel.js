mpmap.service('GenericModalModel', function($modal) {

	var genericModal = {
		show: false,
		title: null,
		value: null,
		open: function(title, value) {
			openModal(title, value);
		},
		close: function() {
			closeModal();
		}
	};

	function openModal(title, value) {
		genericModal.title = title;
		genericModal.value = value;
		genericModal.show = true;
	}

	function closeModal() {
		genericModal.show = false;
		genericModal.title = null;
		genericModal.value = null;
	}

	return function() {
		return genericModal;
	};
});