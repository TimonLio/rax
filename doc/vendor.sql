--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:23:52

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1509 (class 1259 OID 17598)
-- Dependencies: 1777 6
-- Name: vendor; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE vendor (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    comment character varying(256) NOT NULL,
    phone1 character varying(64) NOT NULL,
    phone2 character varying(64) NOT NULL,
    im character varying(64) NOT NULL,
    im_type integer DEFAULT 0 NOT NULL,
    title character varying(64) NOT NULL,
    address character varying(128) NOT NULL
);


ALTER TABLE public.vendor OWNER TO erp;

--
-- TOC entry 1508 (class 1259 OID 17596)
-- Dependencies: 1509 6
-- Name: vendor_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE vendor_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.vendor_id_seq OWNER TO erp;

--
-- TOC entry 1782 (class 0 OID 0)
-- Dependencies: 1508
-- Name: vendor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE vendor_id_seq OWNED BY vendor.id;


--
-- TOC entry 1776 (class 2604 OID 17601)
-- Dependencies: 1508 1509 1509
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE vendor ALTER COLUMN id SET DEFAULT nextval('vendor_id_seq'::regclass);


--
-- TOC entry 1779 (class 2606 OID 17606)
-- Dependencies: 1509 1509
-- Name: vendor_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY vendor
    ADD CONSTRAINT vendor_p_key PRIMARY KEY (id);


-- Completed on 2009-05-04 20:23:53

--
-- PostgreSQL database dump complete
--

