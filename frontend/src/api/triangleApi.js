
const APIBaseUrl = 'http://localhost:8080';
const triangleAPI = '/api/triangle'

const getTriangleClassify = async(payload) => {
    try {
        const res = await fetch(APIBaseUrl+triangleAPI+"/classify" , {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(payload),
        });

        const response = await res.json();
        return response;
    } catch (error) {
        console.error("Error in triangle classify api", error);
        return await { result: "Error", explanation: "Network Connection Error"};
    }
}

const deleteTriangleById = async(id) => {
    const res = await fetch(APIBaseUrl+triangleAPI+`/${id}` , {
        method: "DELETE",
        headers: {"Content-Type": "application/json"},
    });

    // 404 Not Found
    if (res.status === 404) {
        throw new Error(`Triangle with id ${id} not found.`);
    }

    if (!res.ok) {
        const errText = await res.text();
        throw new Error(`Failed to delete : ${errText}`);
    }
    
    // If 204 no content (delete success), return null
    if (res.status === 204) return null;

    return await res.json();

}

const getTriangleRecords = async() => {
    try {
        const res = await fetch(APIBaseUrl+triangleAPI+"/record" , {
            method: "GET",
            headers: {"Content-Type": "application/json"},
        });

        const response = await res.json();
        return response;
    } catch (error) {
        console.error("Error in get triangle records api", error);
        return await { result: "Error", explanation: error};
    }
}

export { getTriangleClassify, deleteTriangleById, getTriangleRecords };