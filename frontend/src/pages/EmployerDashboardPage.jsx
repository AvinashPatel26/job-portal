import { useEffect, useState } from "react";
import MainLayout from "../common/components/MainLayout";
import { Row, Col, Card, Spinner } from "react-bootstrap";
import axios from "axios";

export default function EmployerDashboardPage() {

  const user = JSON.parse(localStorage.getItem("user"));
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/employer/dashboard/stats?employerId=${user.id}`
      );
      setStats(res.data);
    } catch (e) {
      console.log(e);
    } finally {
      setLoading(false);
    }
  };

  return (
    <MainLayout>

      <h2 className="fw-bold mb-4">Employer Dashboard</h2>

      {loading ? (
        <Spinner />
      ) : (
        <Row>
          <Col md={3}>
            <Card className="saas-card gradient-blue">
              <h6>Total Jobs</h6>
              <h2>{stats.totalJobs}</h2>
            </Card>
          </Col>

          <Col md={3}>
            <Card className="saas-card gradient-green">
              <h6>Total Applications</h6>
              <h2>{stats.totalApplications}</h2>
            </Card>
          </Col>

          <Col md={3}>
            <Card className="saas-card gradient-purple">
              <h6>Shortlisted</h6>
              <h2>{stats.shortlisted}</h2>
            </Card>
          </Col>

          <Col md={3}>
            <Card className="saas-card gradient-red">
              <h6>Rejected</h6>
              <h2>{stats.rejected}</h2>
            </Card>
          </Col>
        </Row>
      )}
    </MainLayout>
  );
}
