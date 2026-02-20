import { useEffect, useState } from "react";
import MainLayout from "../common/components/MainLayout";
import axios from "axios";
import { Row, Col, Card, Button } from "react-bootstrap";

export default function EmployerApplicationsPage() {

  const [applications, setApplications] = useState([]);

  const jobId = 1; // later dynamic

  useEffect(() => {
    loadApplications();
  }, []);

  const loadApplications = async () => {
    const res = await axios.get(
      `http://localhost:8080/api/employer/jobs/${jobId}/applications`
    );
    setApplications(res.data);
  };

  const updateStatus = async (id, status) => {
    await axios.put(
      `http://localhost:8080/api/employer/applications/${id}/status?status=${status}`
    );
    loadApplications();
  };

  const filterByStatus = (status) =>
    applications.filter(a => a.status === status);

  const Column = ({ title, status }) => (
    <Col md={4}>
      <Card className="p-3">
        <h5>{title}</h5>

        {filterByStatus(status).map(app => (
          <Card key={app.id} className="p-2 mb-2">
            <strong>{app.applicant.name}</strong>

            <div className="mt-2">
              <Button
                size="sm"
                className="me-2"
                onClick={() => updateStatus(app.id, "SHORTLISTED")}
              >
                Shortlist
              </Button>

              <Button
                size="sm"
                variant="danger"
                onClick={() => updateStatus(app.id, "REJECTED")}
              >
                Reject
              </Button>
            </div>
          </Card>
        ))}
      </Card>
    </Col>
  );

  return (
    <MainLayout>
      <h3 className="mb-4">Hiring Pipeline</h3>

      <Row>
        <Column title="Applied" status="APPLIED" />
        <Column title="Shortlisted" status="SHORTLISTED" />
        <Column title="Rejected" status="REJECTED" />
      </Row>
    </MainLayout>
  );
}
