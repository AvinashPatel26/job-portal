import { Navbar, Nav, Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

export default function AppNavbar() {
  const nav = useNavigate();

  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <Navbar.Brand>JobPortal</Navbar.Brand>
        <Nav className="ms-auto">
          <Nav.Link onClick={() => nav("/dashboard")}>Dashboard</Nav.Link>
          <Nav.Link onClick={() => nav("/jobs")}>Jobs</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}
