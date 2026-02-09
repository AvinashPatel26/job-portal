import { useEffect, useState } from "react";
import { Container, Card, Button } from "react-bootstrap";
import apiClient from "../services/apiClient";

export default function JobListPage() {
  const [jobs, setJobs] = useState([]);

  useEffect(() => {
    apiClient.get("/jobs").then((res) => setJobs(res.data));
  }, []);

  const applyJob = async (jobId) => {
  const token = localStorage.getItem("token");

  await axios.post(
    `http://localhost:8080/api/applications/apply?userId=1&jobId=${jobId}`,
    {},
    { headers: { Authorization: `Bearer ${token}` } }
  );

  alert("Applied successfully");
};


  return (
    <Container className="mt-4">
      <h3>Available Jobs</h3>
      {jobs.map((job) => (
        <Card key={job.id} className="p-3 mb-3 card-shadow">
          <h5>{job.title}</h5>
          <p>
            {job.company} · {job.location}
          </p>
          <Button onClick={() => apply(job.id)}>Apply</Button>
        </Card>
      ))}
    </Container>
  );
}
