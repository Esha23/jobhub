import { createSlice } from "@reduxjs/toolkit";

const initialState = {
	userObject: {},
};

const { actions, reducer } = createSlice({
	name: "user",
	initialState,
	reducers: {
		fetchUserSuccess: (state, { payload }) => {
			state.userObject = payload;
		},
		reset: (state) => {
			state.userObject = {};
		}
	}
});

export const { 
    fetchUserSuccess //action //userreducer
} = actions;
export default reducer;
