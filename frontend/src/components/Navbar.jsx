// src/components/Navbar.jsx
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav style={{ padding: 15, background: "#0a66c2", color: "white" }}>
      <Link to="/" style={{ margin: 10, color: "white" }}>Jobs</Link>
      <Link to="/post" style={{ margin: 10, color: "white" }}>Post Job</Link>
      <Link to="/login" style={{ margin: 10, color: "white" }}>Login</Link>
    </nav>
  );
}
