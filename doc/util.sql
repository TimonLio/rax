--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:23:46

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1503 (class 1259 OID 17486)
-- Dependencies: 6
-- Name: util; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE util (
    key character varying(64) NOT NULL,
    value character varying(256) NOT NULL
);


ALTER TABLE public.util OWNER TO erp;

--
-- TOC entry 1778 (class 0 OID 17486)
-- Dependencies: 1503
-- Data for Name: util; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY util (key, value) FROM stdin;
last_fee_date	2009-03-01
max_token_length	13
\.


--
-- TOC entry 1777 (class 2606 OID 17528)
-- Dependencies: 1503 1503
-- Name: util_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY util
    ADD CONSTRAINT util_p_key PRIMARY KEY (key);


-- Completed on 2009-05-04 20:23:46

--
-- PostgreSQL database dump complete
--

