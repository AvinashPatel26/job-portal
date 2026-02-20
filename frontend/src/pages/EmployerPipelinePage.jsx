import { useEffect, useState } from "react";
import MainLayout from "../common/components/MainLayout";
import axios from "axios";
import { Row, Col, Card, Spinner } from "react-bootstrap";

export default function EmployerPipelinePage() {

  const [pipeline, setPipeline] = useState({});
  const [loading, setLoading] = useState(true);

  const jobId = 1; // later dynamic

  useEffect(() => {
    loadPipeline();
  }, []);

  const loadPipeline = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/employer/pipeline?jobId=${jobId}`
      );
      setPipeline(res.data);
    } finally {
      setLoading(false);
    }
  };

  const columns = [
    "APPLIED",
    "SHORTLISTED",
    "INTERVIEW",
    "REJECTED"
  ];

  return (
    <MainLayout>
      <h3 className="mb-4">Hiring Pipeline</h3>

      {loading ? (
        <Spinner />
      ) : (
        <Row>
          {columns.map(status => (
            <Col key={status}>
              <Card className="p-3">
                <h5>{status}</h5>

                {(pipeline[status] || []).map(app => (
                  <Card key={app.id} className="p-2 mb-2">
                    <strong>{app.applicant.name}</strong>
                    <br />
                    <small>{app.job.title}</small>
                  </Card>
                ))}
              </Card>
            </Col>
          ))}
        </Row>
      )}
    </MainLayout>
  );
}
