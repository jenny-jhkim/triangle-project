import React, {useState, useEffect} from 'react'
import { FormControl, InputLabel, Select,FormHelperText, MenuItem, TextField, Box, Paper, Stack, Button } from '@mui/material'
import SendIcon from '@mui/icons-material/Send'
import {getTriangleClassify} from '../api/triangleApi'
import './TriangleInput.css'

function TriangleInput( {mode, onResult} ) {
    const [inputType, setInputType ] = useState("Sides");
    const [inputs, setInputs] = useState({ a: "", b: "", c: "" });

    const triangleData = {
        inputType,
        a: parseInt(inputs.a),
        b: parseInt(inputs.b),
        c: parseInt(inputs.c),
    };

    let result = {
        result: "",
        explanation: ""
    };

    /*

    useEffect(() => {
        if (mode === "edit" && editData) {
          setInputs(editData); // 기존 값으로 초기화
        }
      }, [mode, editData]);
      */
    
    useEffect( () => {
        getTriangleClassify();
    }, []);

    const handleInputTypeChange = (e) => {
        setInputType(e.target.value);
    }

    const handleInputChange = (e) => {
        setInputs({ ...inputs, [e.target.name]: e.target.value });
    };

    const handleCheck = async() => {

        if(!inputs.a || !inputs.b || !inputs.c) {
            result.result="Input Error"
            result.explanation = "All fields are required."
        }
        else {
            console.log("Check: submit data", triangleData);
            result = await getTriangleClassify(triangleData);
        }
        onResult(result);
    }

    return (

        <Paper elevation={3} className="paper-container">
            <div className="triangle-input-container" >
            <Box>
            <FormControl size="small">
                <InputLabel id="triangle-select-label">Input Type:</InputLabel>
                <Select
                    labelId="triangle-select-label"
                    id="select-inputType"
                    label="Input Type:"
                    value={inputType}
                    onChange={handleInputTypeChange}                     
                >
                    <MenuItem value="Sides">Sides</MenuItem>
                    <MenuItem value="Angles">Angles</MenuItem>
                </Select>
                <FormHelperText>
                    The classification criteria
                </FormHelperText>
            </FormControl>
            </Box>
            <Stack className="inputfields-group" direction="row" spacing={2}>
                <TextField label="A" size="small" required
                    name="a" id="inputA" value={inputs.a} onChange={handleInputChange} />
                <TextField label="B" size="small" required
                    name="b" id="inputB" value={inputs.b} onChange={handleInputChange} />
                <TextField label="C" size="small" required
                    name="c" id="inputC" value={inputs.c} onChange={handleInputChange} />
            </Stack>
            <Stack direction="row" spacing={2}>
                <Button id="checkButton" variant="outlined" endIcon={<SendIcon />} onClick={handleCheck}>Check</Button>
                <Button id="saveButton" variant="contained" color="success" endIcon={<SendIcon />} disabled>Save</Button>
            </Stack>
            </div>
        </Paper>

    )
    
}

export default TriangleInput;