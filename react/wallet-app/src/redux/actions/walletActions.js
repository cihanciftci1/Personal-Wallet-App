import * as types from "./actionTypes";
import axios from "axios";


export const createWallet=(wallet,navigate)=>{
    return async (dispatch)=>{
        try {
            await axios.post("http://localhost:8080/wallet",wallet);
            navigate("/dashboard");
            dispatch(getErrors({}));
        } catch (error) {
            dispatch(getErrors(error.response.data));
        }
    }
}
export const updateWallet=(wallet,walletId,navigate)=>{
    return async (dispatch)=>{
        try {
            await axios.put(`http://localhost:8080/wallet/${walletId}`,wallet);
            navigate("/dashboard");
            dispatch(getErrors({}));
        } catch (error) {
            dispatch(getErrors(error.response.data));
        }
    }
}

export const getErrors=(error)=>{
    return {
        type:types.GET_ERRORS,
        payload:error
    }
}

export const getWallets=()=>{
    return async (dispatch)=>{
        const wallets=(await axios.get("http://localhost:8080/wallet/all")).data;
        dispatch({type:types.GET_WALLETS,payload:wallets});
    }
}
export const getWallet=(walletId)=>{
    return async (dispatch)=>{
        await axios.get(`http://localhost:8080/wallet/${walletId}`).then(res=>{dispatch({type:types.GET_WALLET,payload:res.data});})
        
    }
}

export const deleteWallet=(walletId)=>{
    return async (dispatch)=>{
        await axios.delete(`http://localhost:8080/wallet/${walletId}`)
        dispatch({type:types.DELETE_WALLET,payload:walletId})
    }
}