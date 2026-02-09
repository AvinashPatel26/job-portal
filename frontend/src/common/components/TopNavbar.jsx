import { Navbar, Container, Nav, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

export default function TopNavbar() {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  return (
    <Navbar bg="white" expand="lg" className="shadow-sm">
      <Container fluid>
        <Navbar.Brand className="fw-bold">
          Job Portal
        </Navbar.Brand>

        <Nav className="ms-auto">
          <Nav.Link onClick={() => navigate("/dashboard")}>
            Dashboard
          </Nav.Link>
          <Nav.Link onClick={() => navigate("/jobs")}>
            Jobs
          </Nav.Link>
          <Nav.Link onClick={() => navigate("/applications")}>
            Applications
          </Nav.Link>
          <Nav.Link onClick={() => navigate("/profile")}>
            Profile
          </Nav.Link>

          <Button
            variant="outline-danger"
            size="sm"
            className="ms-3"
            onClick={logout}
          >
            Logout
          </Button>
        </Nav>
      </Container>
    </Navbar>
  );
}
