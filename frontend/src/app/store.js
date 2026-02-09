import { configureStore } from "@reduxjs/toolkit";

import uiReducer from "../common/slices/uiSlice";
import notificationReducer from "../common/slices/notificationSlice";
import authReducer from "../features/auth/authSlice";
import jobReducer from "../features/jobs/jobSlice";
import chatReducer from "../features/chat/chatSlice";

export const store = configureStore({
  reducer: {
    ui: uiReducer,
    notifications: notificationReducer,
    auth: authReducer,
    jobs: jobReducer,
    chat: chatReducer,
  },
});

export default store;
