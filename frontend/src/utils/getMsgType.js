const getMsgType = ( result ) => {
    if(!result || typeof result !== "string") return "success";

    const lowerResult = result.toLowerCase();
    if(lowerResult.includes("error")) return "error";
    if(lowerResult.includes("warning")) return "warning";
    if(lowerResult.includes("info")) return "info";
    if(lowerResult.includes("not a triangle")) return "info";

    return "success";
}

export default getMsgType;