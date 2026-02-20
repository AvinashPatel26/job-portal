import { Routes, Route, Navigate } from "react-router-dom";
import { useSelector } from "react-redux";

import LoginPage from "./pages/LoginPage";
import DashboardPage from "./pages/DashboardPage";
import JobListPage from "./pages/JobListPage";
import ProfilePage from "./pages/ProfilePage";
import MyApplicationsPage from "./pages/MyApplicationsPage";
import ProtectedRoute from "./common/components/ProtectedRoute";
const token = localStorage.getItem("token");


import "./index.css"; 
import EmployerPipelinePage from "./pages/EmployerPipelinePage";
import EmployerDashboardPage from "./pages/EmployerDashboardPage";
export default function App() {
  const token = useSelector((state) => state.auth.token);

  return (
    <Routes>

      {/* ROOT */}
      <Route
        path="/"
        element={token ? <Navigate to="/dashboard" /> : <Navigate to="/login" />}
      />

      {/* LOGIN (Public) */}
      <Route
        path="/login"
        element={token ? <Navigate to="/dashboard" /> : <LoginPage />}
      />

      {/* PROTECTED ROUTES */}
      <Route
        path="/dashboard"
        element={
          <ProtectedRoute>
            <DashboardPage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/jobs"
        element={
          <ProtectedRoute>
            <JobListPage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/profile"
        element={
          <ProtectedRoute>
            <ProfilePage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/applications"
        element={
          <ProtectedRoute>
            <MyApplicationsPage />
          </ProtectedRoute>
        }
      />

      {/* FALLBACK */}
      <Route path="*" element={<Navigate to="/" />} />
      <Route
  path="/dashboard"
  element={token ? <DashboardPage /> : <Navigate to="/login" />}
/>
<Route
  path="/employer-dashboard"
  element={
    <ProtectedRoute>
      <EmployerDashboardPage />
    </ProtectedRoute>
  }
/>
<Route path="/employer/pipeline" element={<EmployerPipelinePage />} />


    </Routes>
  );
}
