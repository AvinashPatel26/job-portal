import Swal from "sweetalert2";

export const showConfirmAlert = async ({
  title = "Are you sure?",
  text = "This action cannot be undone",
  confirmButtonText = "Yes",
}) => {
  return await Swal.fire({
    title,
    text,
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#0d6efd",
    cancelButtonColor: "#dc3545",
    confirmButtonText,
  });
};
