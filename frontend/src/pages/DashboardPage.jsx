import { useEffect, useState } from "react";
import MainLayout from "../common/components/MainLayout";
import { Row, Col, Card, Button, Spinner } from "react-bootstrap";
import axios from "axios";

import {
  ResponsiveContainer,
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  PieChart,
  Pie,
  Cell,
  Legend,
} from "recharts";

export default function DashboardPage() {
  const token = localStorage.getItem("token");

  // TEMP USER ID (until JWT extraction added)
  const userId = 1;

  const [stats, setStats] = useState({
    appliedJobs: 0,
    savedJobs: 0,
    availableJobs: 0,
  });

  const [monthly, setMonthly] = useState([]);
  const [status, setStatus] = useState([]);
  const [recentJobs, setRecentJobs] = useState([]);

  const [loading, setLoading] = useState(true);

  const headers = {
    Authorization: `Bearer ${token}`,
  };

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {
    try {
      const [statsRes, analyticsRes, jobsRes] = await Promise.all([
        axios.get(
          `http://localhost:8080/api/dashboard/stats?userId=${userId}`,
          { headers }
        ),
        axios.get(
          `http://localhost:8080/api/dashboard/analytics?userId=${userId}`,
          { headers }
        ),
        axios.get(
          "http://localhost:8080/api/jobs/recent",
          { headers }
        ),
      ]);

      setStats(statsRes.data || {});
      setMonthly(analyticsRes.data?.applicationsPerMonth || []);
      setStatus(analyticsRes.data?.statusBreakdown || []);
      setRecentJobs(jobsRes.data || []);
    } catch (err) {
      console.error("Dashboard load failed", err);
    } finally {
      setLoading(false);
    }
  };

  const pieColors = ["#0d6efd", "#198754", "#ffc107", "#dc3545"];

  return (
    <MainLayout>
      {/* HEADER */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
          <h2 className="fw-bold mb-1">Dashboard</h2>
          <p className="text-muted">Your job activity overview</p>
        </div>
        <Button className="px-4">Browse Jobs</Button>
      </div>

      {/* STATS */}
      <Row className="mb-4">
        {loading ? (
          <Spinner />
        ) : (
          <>
            <Col md={4}>
              <Card className="saas-card gradient-blue">
                <h6>Applied Jobs</h6>
                <h2>{stats?.appliedJobs ?? 0}</h2>
                <small>Applications submitted</small>
              </Card>
            </Col>

            <Col md={4}>
              <Card className="saas-card gradient-green">
                <h6>Saved Jobs</h6>
                <h2>{stats?.savedJobs ?? 0}</h2>
                <small>Jobs bookmarked</small>
              </Card>
            </Col>

            <Col md={4}>
              <Card className="saas-card gradient-purple">
                <h6>Available Jobs</h6>
                <h2>{stats?.availableJobs ?? 0}</h2>
                <small>Live opportunities</small>
              </Card>
            </Col>
          </>
        )}
      </Row>

      {/* ANALYTICS */}
      <Row className="mb-4">
        <Col md={8}>
          <Card className="section-card p-3">
            <h5 className="mb-3">Applications Trend</h5>

            {loading ? (
              <Spinner />
            ) : (
              <ResponsiveContainer width="100%" height={300}>
                <LineChart data={monthly}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="month" />
                  <YAxis />
                  <Tooltip />
                  <Line
                    type="monotone"
                    dataKey="count"
                    stroke="#0d6efd"
                    strokeWidth={3}
                  />
                </LineChart>
              </ResponsiveContainer>
            )}
          </Card>
        </Col>

        <Col md={4}>
          <Card className="section-card p-3">
            <h5 className="mb-3">Application Status</h5>

            {loading ? (
              <Spinner />
            ) : (
              <ResponsiveContainer width="100%" height={300}>
                <PieChart>
                  <Pie
                    data={status}
                    dataKey="value"
                    nameKey="name"
                    outerRadius={90}
                  >
                    {status.map((_, i) => (
                      <Cell key={i} fill={pieColors[i % 4]} />
                    ))}
                  </Pie>
                  <Tooltip />
                  <Legend />
                </PieChart>
              </ResponsiveContainer>
            )}
          </Card>
        </Col>
      </Row>

      {/* RECENT JOBS */}
      <Card className="section-card p-4">
        <h5 className="mb-3">Recent Jobs</h5>

        {recentJobs.length === 0 ? (
          <p className="text-muted">No recent jobs</p>
        ) : (
          recentJobs.map((job) => (
            <div key={job.id} className="mb-3">
              <strong>{job.title}</strong>
              <br />
              <span className="text-muted">{job.company}</span>
              <hr />
            </div>
          ))
        )}
      </Card>
    </MainLayout>
  );
}
