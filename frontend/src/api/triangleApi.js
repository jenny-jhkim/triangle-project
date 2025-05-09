
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

const createTriangle = async(payload) => {
    try {
        const res = await fetch(APIBaseUrl+triangleAPI+"/save" , {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(payload),
        });
    
        if(res.status === 201) {
            return { result: "", explanation: "Saved to database!"};
        } else {
            const error = await res.json();
            return { result: "Error", explanation: error};
        }

    } catch (error) {
        console.error("Fetch error in createTriangle api, ", error);
        return { result: "Error", explanation: error};
    }
}

const deleteTriangleById = async(id) => {
    const res = await fetch(APIBaseUrl+triangleAPI+`/${id}` , {
        method: "DELETE",
        headers: {"Content-Type": "application/json"},
    });
    console.error("Delete Triangle by ID: " + id + " res.status: " + res.status);

    // 404 Not Found by id
    if(res.status === 404) {
        return { result: "Error", explanation: `Triangle with id ${id} not found.`};
    }

    if(!res.ok) {
        const errText = await res.text();
        return { result: "Error. Failed to delete", explanation: errText};
    }

    // If 204 no content (delete success), return null
    if (res.status === 204) return { result: "success", explanation: `Triangle ID ${id} : deleted successfully.`};;

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

export { getTriangleClassify, createTriangle, deleteTriangleById, getTriangleRecords };