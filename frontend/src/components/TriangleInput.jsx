import React, {useState, useEffect} from 'react'
import { FormControl, InputLabel, Select,FormHelperText, MenuItem, TextField, Box, Paper, Stack, Button } from '@mui/material'
import SendIcon from '@mui/icons-material/Send'
import SaveIcon from '@mui/icons-material/Save'
import {getTriangleClassify , createTriangle } from '../api/triangleApi'
import getMsgType from '../utils/getMsgType'
import './TriangleInput.css'

function TriangleInput( {mode, onResult} ) {
    const [inputType, setInputType ] = useState("Sides");
    const [inputs, setInputs] = useState({ a: "", b: "", c: "" });
    const [result, setResult] = useState( {result: "", explanation: ""});
    const [saveEnable, setEnable] = useState(false);

    const triangleData = {
        inputType,
        a: parseInt(inputs.a),
        b: parseInt(inputs.b),
        c: parseInt(inputs.c),
    };

    /*
    useEffect(() => {
        if (mode === "edit" && editData) {
          setInputs(editData);
        } else {
          clearData();
        }
      }, [mode, editData]);
      */
    
    useEffect( ()=> {
        if(result && ( result.result || result.explanation )) {
            onResult(result);
        };
    }, [result]);

    useEffect( () => {
        setEnable(false); // Prevent saving unintended values without checking

    } , [inputType, inputs]);

    const handleInputTypeChange = (e) => {
        setInputType(e.target.value);
    };

    const handleInputChange = (e) => {
        setInputs({ ...inputs, [e.target.name]: e.target.value });
    };

    const handleCheck = async() => {

        if(!inputs.a || !inputs.b || !inputs.c) {
            setResult({
                result: "Input Error",
                explanation: "All fields are required."
            });
            return;
        }
        else {
            console.log("Check: submit data", triangleData);
            const res = await getTriangleClassify(triangleData);
            setResult(res);

            //enable the save button
            const type = getMsgType(res.result);
            if(type === 'success' || type ==='info')
            {
                setTimeout( ()=> {
                    setEnable(true);
                }, 500);
            } else {
                setEnable(false);
            }
        }
    }

    const handleSave = async() => {

        const triangleData = {
            inputType,
            a: parseInt(inputs.a),
            b: parseInt(inputs.b),
            c: parseInt(inputs.c),
            triangleType: result.result
        };

        try{
            const response = await createTriangle(triangleData);
            console.log("Saved: res", response);
            clearData();
            setResult(response);
        } catch (error) {
            result.result = "Save Error"
            result.explanation = error;
            onResult(result);
        }
    }

    const clearData = () => {
        setInputs({ a: "", b: "", c: "" });
        setResult({result: "", explanation: ""});
        setEnable(false);
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
                <Button id="checkButton"
                    variant="outlined"
                    endIcon={<SendIcon />}
                    onClick={handleCheck}
                > Check </Button>
                <Button id="saveButton"
                    variant="contained"
                    color="success"
                    endIcon={<SaveIcon />}
                    onClick={handleSave}
                    disabled={!saveEnable}
                > {mode==="save" ? "save" : "UPDATE"} </Button>
            </Stack>
            </div>
        </Paper>

    )
    
}

export default TriangleInput;