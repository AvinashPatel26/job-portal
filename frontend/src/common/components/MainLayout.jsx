import { NavLink, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { logout } from "../../features/auth/authSlice";

export default function MainLayout({ children }) {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logout());
    navigate("/login");
  };

  return (
    <div className="app-layout">

      {/* SIDEBAR */}
      <aside className="sidebar">
        <h4 className="logo">Job Portal</h4>

        <nav className="menu">
          <NavLink to="/dashboard">Dashboard</NavLink>
          <NavLink to="/jobs">Browse Jobs</NavLink>
          <NavLink to="/applications">Applications</NavLink>
          <NavLink to="/profile">Profile</NavLink>
        </nav>
      </aside>

      {/* MAIN AREA */}
      <div className="main-area">

        {/* TOPBAR */}
        <header className="topbar">
          <h5 className="page-title">Dashboard</h5>

          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        {/* CONTENT */}
        <main className="content-area">
          {children}
        </main>

      </div>
    </div>
  );
}
