import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import persistReducer from 'redux-persist/es/persistReducer';
import rootReducer from './slices';
import storage from 'redux-persist/lib/storage'

const middleware = getDefaultMiddleware({ immutableCheck: false, serializableCheck: false });

const persistConfig = {
    key: 'root',
    storage
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({ reducer: persistedReducer, middleware });
