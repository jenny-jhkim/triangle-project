import React, { useState, useEffect } from 'react'
import {
    TableContainer, Table, TableBody, TableCell, TableHead, TableRow, 
    Box, Paper, Button
} from '@mui/material'
import {getTriangleRecords, deleteTriangleById} from '../api/triangleApi'
import './TriangleRecord.css'
import { prefetchDNS } from 'react-dom'


function TriangleRecord() {
    const [triangles, setTriangles] = useState([]);

    useEffect( ()=> {
        getTriangleRecords()
        .then(data => {
            console.log("Received data: ", data);
            if(data.length > 0) {
                setTriangles(data``);
            }
        })
        .catch(error => {
            console.error("Backend error: ", error);
            setTriangles([]);
        });
    }, []);

    const handleEdit = (id) => {
        alert(`Edit feature coming soon`);
    };
    
    const handleDelete = async(id) => {
        await deleteTriangleById(id);
        setTriangles(prev => prefetchDNS.filter(triangle => triangle.id !== id));
    }


    return (
        <div>
            <h1 className='main-title-container'>Triangle Records</h1>
            <Box className='record-table-container'>
            <TableContainer component={Paper}>
            <Table size="small" aria-label="triangle record table">
                <TableHead className="table-header">
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Input Type</TableCell>
                        <TableCell>A</TableCell>
                        <TableCell>B</TableCell>
                        <TableCell>C</TableCell>
                        <TableCell>Triangle Type</TableCell>
                        <TableCell>Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {triangles && triangles.map( (row) => (
                    <TableRow key={row.id}>
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{row.inputType}</TableCell>
                    <TableCell>{row.a}</TableCell>
                    <TableCell>{row.b}</TableCell>
                    <TableCell>{row.c}</TableCell>

                    <TableCell>{row.triangleType}</TableCell>
                    <TableCell>
                        <Button variant="outlined" color="primary" onClick={() => handleEdit(row.id)} style={{ marginRight: '0.5rem' }}>
                        Edit
                        </Button>
                        <Button variant="outlined" color="error" onClick={() => handleDelete(row.id)}>
                        Delete
                        </Button>
                    </TableCell>
                    </TableRow>
                    ))}                    
                </TableBody>
            </Table>
            </TableContainer>
            {triangles.length == 0 && <p>No data found</p>}
            </Box>
        </div>
    );
}

export default TriangleRecord;