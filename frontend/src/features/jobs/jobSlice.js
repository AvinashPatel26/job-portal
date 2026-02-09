import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { fetchJobs } from "./jobService";

export const loadJobsAsync = createAsyncThunk(
  "jobs/loadJobs",
  async () => {
    return await fetchJobs();
  }
);

const jobSlice = createSlice({
  name: "jobs",
  initialState: {
    jobList: [],
    isLoading: false,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(loadJobsAsync.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(loadJobsAsync.fulfilled, (state, action) => {
        state.jobList = action.payload;
        state.isLoading = false;
      })
      .addCase(loadJobsAsync.rejected, (state) => {
        state.isLoading = false;
      });
  },
});

export default jobSlice.reducer;
