import { useEffect, useState } from "react";
import MainLayout from "../common/components/MainLayout";
import { Card, Spinner } from "react-bootstrap";
import axios from "axios";

export default function MyApplicationsPage() {

  const token = localStorage.getItem("token");

  // TEMP until JWT decode added
  const userId = localStorage.getItem("userId") || 1;

  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadApplications();
  }, []);

  const loadApplications = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/applications/my?userId=${userId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      setApplications(res.data);
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <MainLayout>
      <h2 className="mb-4">My Applications</h2>

      {loading ? (
        <Spinner />
      ) : (
        applications.map((app) => (
          <Card key={app.id} className="p-3 mb-3">
            <h5>{app.job?.title}</h5>
            <p className="text-muted mb-1">
              {app.job?.company}
            </p>
            <strong>Status: {app.status}</strong>
          </Card>
        ))
      )}
    </MainLayout>
  );
}
