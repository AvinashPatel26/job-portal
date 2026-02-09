import { useEffect, useState } from "react";
import MainLayout from "../common/components/MainLayout";
import axios from "axios";
import { Card } from "react-bootstrap";

export default function EmployerDashboard() {

  const [jobs, setJobs] = useState([]);

  useEffect(() => {
    axios.get(
      "http://localhost:8080/api/employer/jobs?employerId=1"
    ).then(res => setJobs(res.data));
  }, []);

  return (
    <MainLayout>
      <h2>My Posted Jobs</h2>

      {jobs.map(job => (
        <Card key={job.id} className="p-3 mb-3">
          <h5>{job.title}</h5>
          <p>{job.company}</p>
        </Card>
      ))}
    </MainLayout>
  );
}
