import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import transactionReducer from "./transactionReducer";
import walletReducer from "./walletReducer";

const rootReducer=combineReducers({
    error:errorReducer,
    walletReducer:walletReducer,
    transactionReducer:transactionReducer
})

export default rootReducer;