import { createSlice } from "@reduxjs/toolkit";

const notificationSlice = createSlice({
  name: "notifications",
  initialState: [],
  reducers: {
    addNotification: (state, action) => {
      state.push(action.payload);
    },
    clearAllNotifications: () => [],
  },
});

export const { addNotification, clearAllNotifications } =
  notificationSlice.actions;

export default notificationSlice.reducer;
