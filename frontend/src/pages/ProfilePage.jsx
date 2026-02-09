import { useSelector, useDispatch } from "react-redux";
import { Button, Card, Container } from "react-bootstrap";
import { logout } from "../features/auth/authSlice";
import { useNavigate } from "react-router-dom";

export default function ProfilePage() {
  const user = useSelector((state) => state.auth.currentUser);
  const dispatch = useDispatch();
  const nav = useNavigate();

  const handleLogout = () => {
    dispatch(logout());
    localStorage.removeItem("token");
    nav("/login");
  };

  return (
    <Container className="mt-4">
      <Card className="p-4 card-shadow">
        <h3>My Profile</h3>
        <p><b>Name:</b> {user.name}</p>
        <p><b>Email:</b> {user.email}</p>
        <p><b>Role:</b> {user.role}</p>
        <Button variant="danger" onClick={handleLogout}>Logout</Button>
      </Card>
    </Container>
  );
}
