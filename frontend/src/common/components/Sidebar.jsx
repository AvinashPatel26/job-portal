import { Nav } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

export default function Sidebar() {
  const navigate = useNavigate();

  return (
    <div className="sidebar">
      <h5 className="mb-4">Menu</h5>

      <Nav className="flex-column">
        <Nav.Link onClick={() => navigate("/dashboard")}>
          Dashboard
        </Nav.Link>

        <Nav.Link onClick={() => navigate("/jobs")}>
          Browse Jobs
        </Nav.Link>

        <Nav.Link onClick={() => navigate("/applications")}>
          My Applications
        </Nav.Link>

        <Nav.Link onClick={() => navigate("/profile")}>
          Profile
        </Nav.Link>
      </Nav>
    </div>
  );
}
